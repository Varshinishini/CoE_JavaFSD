document.addEventListener("DOMContentLoaded", () => {
    loadRoutines();
});

let editIndex = null; // To track which routine is being edited

function loadRoutines() {
    const routineList = document.getElementById("routine-list");
    routineList.innerHTML = ""; 
    let storedRoutines = JSON.parse(localStorage.getItem("routines")) || [];

    const groupedRoutines = {};
    storedRoutines.forEach((routine, index) => {
        if (!groupedRoutines[routine.date]) {
            groupedRoutines[routine.date] = [];
        }
        groupedRoutines[routine.date].push({ ...routine, index });
    });

    Object.keys(groupedRoutines).forEach(date => {
        const dateHeading = document.createElement("h2");
        dateHeading.textContent = date;
        dateHeading.classList.add("routine-date");
        routineList.appendChild(dateHeading);

        groupedRoutines[date].forEach(routine => {
            const routineItem = document.createElement("div");
            routineItem.classList.add("routine-item");
            routineItem.innerHTML = `
                <div class="routine-details">
                    <strong>${routine.type}</strong> - ${routine.duration} mins
                </div>
                <div class="buttons">
                    <button class="edit-btn" onclick="openEditModal(${routine.index})">Edit</button>
                    <button class="complete-btn" onclick="completeRoutine(${routine.index})">Complete</button>
                    <button class="delete-btn" onclick="deleteRoutine(${routine.index})">Delete</button>
                </div>
            `;
            routineList.appendChild(routineItem);
        });
    });
}

function openEditModal(index) {
    let storedRoutines = JSON.parse(localStorage.getItem("routines")) || [];
    editIndex = index;
    document.getElementById("edit-type").value = storedRoutines[index].type;
    document.getElementById("edit-duration").value = storedRoutines[index].duration;
    document.getElementById("edit-modal").style.display = "flex";
}

document.getElementById("edit-form").addEventListener("submit", function(event) {
    event.preventDefault();
    let storedRoutines = JSON.parse(localStorage.getItem("routines")) || [];
    storedRoutines[editIndex].type = document.getElementById("edit-type").value;
    storedRoutines[editIndex].duration = document.getElementById("edit-duration").value;
    localStorage.setItem("routines", JSON.stringify(storedRoutines));
    document.getElementById("edit-modal").style.display = "none";
    loadRoutines();
});

document.querySelector(".close").addEventListener("click", () => {
    document.getElementById("edit-modal").style.display = "none";
});

function completeRoutine(index) {
    let storedRoutines = JSON.parse(localStorage.getItem("routines")) || [];
    let completedRoutine = storedRoutines.splice(index, 1)[0];

    let completedList = JSON.parse(localStorage.getItem("completedRoutines")) || [];
    completedList.push(completedRoutine);
    localStorage.setItem("completedRoutines", JSON.stringify(completedList));

    localStorage.setItem("routines", JSON.stringify(storedRoutines));
    loadRoutines();
}

function deleteRoutine(index) {
    let storedRoutines = JSON.parse(localStorage.getItem("routines")) || [];
    storedRoutines.splice(index, 1);
    localStorage.setItem("routines", JSON.stringify(storedRoutines));
    loadRoutines();
}
