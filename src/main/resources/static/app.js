const form = document.getElementById("application-form");
const message = document.getElementById("message");
const cancelButton = document.getElementById("cancel-edit");
const submitButton = form.querySelector('button[type="submit"]');

let editingId = null;


document.getElementById("view-applications").addEventListener("click", () => {
    window.location.href = "/applications.html";
});

async function loadApplicationForEdit() {
    const params = new URLSearchParams(window.location.search);
    const id = params.get("edit");

    if (id === null) {
        return;
    }

    try {
        const response = await fetch(`/applications/${id}`);

        if (!response.ok) {
            throw new Error("Could not load application.");
        }

        const application = await response.json();

        editingId = application.id;
        document.getElementById("company").value = application.company;
        document.getElementById("job-title").value = application.jobTitle;
        document.getElementById("status").value = application.status;

        document.getElementById("form-title").textContent =
            "Edit an Application";
        submitButton.textContent = "Save Changes";
        cancelButton.hidden = false;
    } catch (error) {
        message.textContent = error.message;
    }
}

cancelButton.addEventListener("click", () => {
    editingId = null;
    form.reset();
    submitButton.textContent = "Add Application";
    document.getElementById("form-title").textContent =
        "Add an Application";
    cancelButton.hidden = true;
    message.textContent = "";

    window.history.replaceState(null, "", "/");
});

form.addEventListener("submit", async (event) => {
    event.preventDefault();
    message.textContent = "";

    const formData = new FormData(form);

    const application = {
        company: formData.get("company"),
        jobTitle: formData.get("jobTitle"),
        status: formData.get("status")
    };

    const url = editingId === null
        ? "/applications"
        : `/applications/${editingId}`;

    const method = editingId === null ? "POST" : "PUT";

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(application)
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || "Could not save application.");
        }

        window.location.href = "/applications.html";
    } catch (error) {
        message.textContent = error.message;
    }
});

loadApplicationForEdit();