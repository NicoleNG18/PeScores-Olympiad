function displayTodo(e) {


    document.querySelector("#taskDescr").value=document.querySelector("#todo").value;
    document.querySelector("#dueDateField").value=document.querySelector("#dueDate").value;

    let tableBody = document.querySelector(".table-body");
    let tableRow = document.createElement("tr");
    let description = document.querySelector("#todo").value;
    let date=document.querySelector("#dueDate").value;

    tableRow.innerHTML = `
                   <td>${description}</td>
                   <td>Finish until  ${date}</td>
                    <td class="done">
                        <form>
                        <button type="submit" title="Done"><svg xmlns="http://www.w3.org/2000/svg" height="16" width="14" viewBox="0 0 448 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M438.6 105.4c12.5 12.5 12.5 32.8 0 45.3l-256 256c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0L160 338.7 393.4 105.4c12.5-12.5 32.8-12.5 45.3 0z"/></svg></button>
                        </form>
                    </td>`;

    tableBody.appendChild(tableRow);

    document.querySelector("#todo").value="";
    document.querySelector("#dueDate").value="";

}
