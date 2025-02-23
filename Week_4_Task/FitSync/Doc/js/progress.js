document.addEventListener("DOMContentLoaded", () => {
    displayProgressChart();
});

function displayProgressChart() {
    let completedRoutines = JSON.parse(localStorage.getItem("completedRoutines")) || [];
    
    let progressData = {};
    completedRoutines.forEach(routine => {
        if (!progressData[routine.date]) {
            progressData[routine.date] = 0;
        }
        progressData[routine.date] += parseInt(routine.duration);
    });

    const dates = Object.keys(progressData);
    const durations = Object.values(progressData);

    const ctx = document.getElementById('progressChart').getContext('2d');
    
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: dates,
            datasets: [{
                label: 'Workout Duration (mins)',
                data: durations,
                backgroundColor: ['#0071FF', '#4CAF50', '#FF4C4C', '#FFA500'],
                borderColor: '#fff',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: true,
                    max: 180, // ✨ Set a fixed max limit (adjust based on your need)
                    ticks: {
                        stepSize: 30, // ✨ Adjust step size for better visibility
                        color: "#c9c9c9"
                    }
                },
                x: {
                    ticks: {
                        color: "#c9c9c9"
                    }
                }
            },
            animation: {
                duration: 2000,
                easing: 'easeOutBounce'
            }
        }
    });
    
}
