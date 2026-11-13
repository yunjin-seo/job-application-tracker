async function loadApplications() {
    const list = document.getElementById("application-list");
    const message = document.getElementById("message");

    message.textContent = "Loading...";
    list.innerHTML = "";

    try {
        const response = await fetch("/applications");

        if (!response.ok) {
            throw new Error("Could not load applications.");
        }

        const applications = await response.json();
        message.textContent = applications.length === 0
            ? "No applications yet."
            : "";

        for (const application of applications) {
            const item = document.createElement("li");
            item.textContent =
                `${application.company} — ${application.jobTitle} (${application.status})`;
            list.appendChild(item);
        }
    } catch (error) {
        message.textContent = error.message;
    }
}
const form = document.getElementById("application-form");

form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const message = document.getElementById("message");
    const formData = new FormData(form);

    const application = {
        company: formData.get("company"),
        jobTitle: formData.get("jobTitle"),
        status: formData.get("status")
    };

    try {
        const response = await fetch("/applications", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(application)
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || "Could not add application.");
        }

        form.reset();
        await loadApplications();
    } catch (error) {
        message.textContent = error.message;
    }
});
loadApplications();