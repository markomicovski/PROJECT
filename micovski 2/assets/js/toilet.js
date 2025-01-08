document.addEventListener('DOMContentLoaded', () => {
    const tasksKey = 'toilet-tasks';
    const progressKey = 'toilet-progress';

    const defaultTasks = [
        { text: 'Fix the shower', completed: false },
        { text: 'Replace toilet seat', completed: false },
        { text: 'Re-tile the floor', completed: false },
        { text: 'Install new sink', completed: false },
        { text: 'Paint walls', completed: false },
    ];

    const taskForm = document.getElementById('task-form');
    const taskInput = document.getElementById('task-input');
    const taskList = document.getElementById('task-list');

    // Load tasks from localStorage or initialize with default tasks
    const loadTasks = () => {
        const savedTasks = JSON.parse(localStorage.getItem(tasksKey));
        if (!savedTasks || !Array.isArray(savedTasks)) {
            localStorage.setItem(tasksKey, JSON.stringify(defaultTasks));
            return defaultTasks;
        }
        return savedTasks;
    };

    // Save tasks to localStorage and update the progress bar
    const saveTasks = (tasks) => {
        localStorage.setItem(tasksKey, JSON.stringify(tasks));
        updateProgress(); // Update progress bar after saving tasks
    };

    // Render tasks on the page
    const renderTasks = () => {
        const tasks = loadTasks();

        // Clear existing tasks
        taskList.innerHTML = '';

        tasks.forEach((task, index) => {
            const taskItem = document.createElement('li');
            taskItem.className = 'task-item';
            if (task.completed) taskItem.classList.add('completed');

            taskItem.innerHTML = `
                <span class="task-text">${task.text}</span>
                <button class="mark-complete">${task.completed ? 'Undo' : 'Complete'}</button>
                <button class="delete-task">Delete</button>
            `;

            // Mark Complete button
            taskItem.querySelector('.mark-complete').addEventListener('click', () => {
                tasks[index].completed = !tasks[index].completed;
                saveTasks(tasks);
                renderTasks();
            });

            // Delete button
            taskItem.querySelector('.delete-task').addEventListener('click', () => {
                tasks.splice(index, 1);
                saveTasks(tasks);
                renderTasks();
            });

            taskList.appendChild(taskItem);
        });
    };

    // Update the progress bar
    const updateProgress = () => {
        const tasks = loadTasks();
        const completedTasks = tasks.filter((task) => task.completed).length;
        const progress = tasks.length ? (completedTasks / tasks.length) * 100 : 0;

        // Save progress in localStorage
        localStorage.setItem(progressKey, Math.round(progress));

        // Update the progress bar visually
        const progressBar = document.querySelector('.progress-bar');
        if (progressBar) {
            progressBar.style.width = `${progress}%`;
            progressBar.setAttribute('data-progress', Math.round(progress));
        }

        // Ensure dashboard progress is updated
        if (typeof updateDashboardProgress === 'function') {
            updateDashboardProgress('toilet');
        }
    };

    // Handle adding a new task
    const addTask = (e) => {
        e.preventDefault(); // Prevent form from submitting and reloading the page
        const taskText = taskInput.value.trim();
        if (!taskText) return; // Ignore empty input

        const tasks = loadTasks();
        tasks.push({ text: taskText, completed: false }); // Add the new task
        saveTasks(tasks);
        renderTasks();
        taskInput.value = ''; // Clear the input field
    };

    // Attach event listener to the form
    taskForm.addEventListener('submit', addTask);

    // Initialize tasks and progress bar
    renderTasks();
    updateProgress();

    // Ensure dashboard progress is updated when leaving the page
    window.addEventListener('beforeunload', () => {
        if (typeof updateDashboardProgress === 'function') {
            updateDashboardProgress('toilet');
        }
    });
});
