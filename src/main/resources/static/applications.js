const statusFilter = document.getElementById("status-filter");
const message = document.getElementById("message");
const list = document.getElementById("application-list");

document.getElementById("add-application").addEventListener("click", () => {
    window.location.href = "/";
});

async function loadApplications() {
    message.textContent = "Loading...";
    list.innerHTML = "";

    try {
        const response = await fetch("/applications");

        if (!response.ok) {
            throw new Error("Could not load applications.");
        }

        const applications = await response.json();
        const selectedStatus = statusFilter.value;

        const visibleApplications = selectedStatus === "ALL"
            ? applications
            : applications.filter(
                application => application.status === selectedStatus
            );

        message.textContent = visibleApplications.length === 0
            ? "No applications found."
            : "";

        for (const application of visibleApplications) {
            const item = document.createElement("li");
            item.textContent =
                `${application.company} — ${application.jobTitle} (${application.status})`;

            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Delete";

            deleteButton.addEventListener("click", async () => {
                try {
                    const response = await fetch(
                        `/applications/${application.id}`,
                        { method: "DELETE" }
                    );

                    if (!response.ok) {
                        throw new Error("Could not delete application.");
                    }

                    await loadApplications();
                } catch (error) {
                    message.textContent = error.message;
                }
            });

            item.appendChild(deleteButton);

            const editButton = document.createElement("button");
            editButton.textContent = "Edit";

            editButton.addEventListener("click", () => {
                window.location.href = `/?edit=${application.id}`;
            });

            item.appendChild(editButton);
            list.appendChild(item);
        }
    } catch (error) {
        message.textContent = error.message;
    }
}

statusFilter.addEventListener("change", loadApplications);

loadApplications();