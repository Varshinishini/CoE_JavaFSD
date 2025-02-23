document.getElementById('routine-form').addEventListener('submit', function(event) {
    event.preventDefault();

    let routineType = document.getElementById('routine-type').value;
    let duration = document.getElementById('duration').value;
    let date = document.getElementById('date').value;

    let routine = { routineType, duration, date, completed: false };

    let routines = JSON.parse(localStorage.getItem('routines')) || [];
    routines.push(routine);
    localStorage.setItem('routines', JSON.stringify(routines));

    alert('Routine added successfully!');
});
