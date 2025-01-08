document.addEventListener('DOMContentLoaded', () => {
    const taskList = document.getElementById('task-list');
    const taskForm = document.getElementById('task-form');
    const taskInput = document.getElementById('task-input');
    const progressBar = document.querySelector('.progress-bar');
    const themeToggleSwitch = document.getElementById('theme-toggle-switch');

    // Load tasks from localStorage or initialize with default tasks
    const loadTasksFromLocalStorage = (key, defaultTasks) => {
        let tasks = JSON.parse(localStorage.getItem(key));

        if (!tasks || !Array.isArray(tasks)) {
            tasks = defaultTasks;
            localStorage.setItem(key, JSON.stringify(tasks));
        }

        taskList.innerHTML = '';
        tasks.forEach((task) => {
            const newTask = createTaskElement(task.text, task.completed);
            taskList.appendChild(newTask);
        });

        updateProgressBar(key);
    };

    // Save tasks to localStorage
    const saveTasksToLocalStorage = (key) => {
        const tasks = Array.from(taskList.children).map((task) => ({
            text: task.querySelector('.task-text').textContent.trim(),
            completed: task.classList.contains('completed'),
        }));
        localStorage.setItem(key, JSON.stringify(tasks));
        updateProgressBar(key);
    };

    // Update the progress bar based on completed tasks
    const updateProgressBar = (progressKey) => {
        const totalTasks = taskList ? taskList.children.length : 0;
        const completedTasks = taskList ? taskList.querySelectorAll('.completed').length : 0;
        const progress = totalTasks ? (completedTasks / totalTasks) * 100 : 0;

        if (progressBar) {
            progressBar.style.width = `${progress}%`;
            progressBar.setAttribute('data-progress', Math.round(progress));
        }

        localStorage.setItem(`${progressKey}-progress`, Math.round(progress));
        updateDashboardProgress(progressKey);
    };

    // Update the dashboard progress bar and text
    const updateDashboardProgress = (key = null) => {
        const projects = [
            { id: 'living-room', name: 'Living Room' },
            { id: 'kitchen', name: 'Kitchen' },
            { id: 'bedroom', name: 'Bedroom' },
            { id: 'toilet', name: 'Toilet' },
        ];

        projects.forEach((project) => {
            if (key && project.id !== key) return;

            const progressBar = document.getElementById(`${project.id}-progress-bar`);
            const progressText = document.getElementById(`${project.id}-progress-text`);
            const progress = localStorage.getItem(`${project.id}-progress`) || 0;

            if (progressBar && progressText) {
                progressBar.style.width = `${progress}%`;
                progressText.textContent = `${Math.round(progress)}% Complete`;
            }
        });
    };

    // Create a new task element
    const createTaskElement = (text, completed = false) => {
        const task = document.createElement('li');
        task.className = 'task-item';
        if (completed) task.classList.add('completed');

        task.innerHTML = `
            <span class="task-text">${text}</span>
            <button class="mark-complete">${completed ? 'Undo' : 'Complete'}</button>
            <button class="delete-task">Delete</button>
        `;

        return task;
    };

    // Add a new task
    const addNewTask = (e, key) => {
        e.preventDefault();
        const taskText = taskInput.value.trim();
        if (!taskText) return;

        const newTask = createTaskElement(taskText);
        taskList.appendChild(newTask);
        taskInput.value = '';
        saveTasksToLocalStorage(key);
    };

    // Handle task actions (Complete/Undo/Delete)
    const handleTaskActions = (e, key) => {
        const task = e.target.closest('.task-item');

        if (e.target.classList.contains('mark-complete')) {
            task.classList.toggle('completed');
            e.target.textContent = task.classList.contains('completed') ? 'Undo' : 'Complete';
        } else if (e.target.classList.contains('delete-task')) {
            task.remove();
        }

        saveTasksToLocalStorage(key);
    };

    // Shared initialization function
    const initializeTaskList = (key, defaultTasks) => {
        taskForm?.removeEventListener('submit', (e) => addNewTask(e, key));
        taskList?.removeEventListener('click', (e) => handleTaskActions(e, key));

        taskForm.addEventListener('submit', (e) => addNewTask(e, key));
        taskList.addEventListener('click', (e) => handleTaskActions(e, key));
        loadTasksFromLocalStorage(key, defaultTasks);

        updateDashboardProgress(key);
    };

    // Export the initialization function for specific pages
    window.initializeTaskList = initializeTaskList;

    // --- Theme Toggle Logic ---
    const initializeTheme = () => {
        const savedTheme = localStorage.getItem('theme') || 'light';
        const isDarkMode = savedTheme === 'dark';
        document.body.classList.toggle('dark-mode', isDarkMode);
        themeToggleSwitch.checked = isDarkMode;
    };

    const toggleTheme = () => {
        const isDarkMode = themeToggleSwitch.checked;
        document.body.classList.toggle('dark-mode', isDarkMode);
        localStorage.setItem('theme', isDarkMode ? 'dark' : 'light');
    };

    initializeTheme();
    themeToggleSwitch.addEventListener('change', toggleTheme);

    updateDashboardProgress();
});
