const projectsContainer = document.querySelector("#projects-container");

const projectCards = [];
for (let i = 0; i < 9; i++) {
  projectCards.push(`
    <div class="card mb-3" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">Special title treatment</h5>
            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
            <a href="#" class="btn btn-primary">Go somewhere</a>
        </div>
    </div>
  `);
}

projectsContainer.innerHTML = projectCards.join();
