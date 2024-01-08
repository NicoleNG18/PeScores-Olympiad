let addButton = document.getElementById('add-btn');

// addButton.addEventListener('submit', onClickAdd);
let csrfHeaderName = document.getElementById("csrf").getAttribute("name");
let csrfHeaderToken = document.getElementById("csrf").getAttribute("value");


// addButton.addEventListener('click',onClickSave);

// let toDo= document.querySelector("#todo").value;

// function onClickSave(toDo){
//
//         // const csrfHeaderName = $("meta[name='_csrf_header']").attr("content");
//         // const csrfHeaderToken = $("meta[name='_csrf']").attr("content");
//
//         let taskDto={
//                 description: toDo
//         }
//
//                 fetch("http://localhost:8080/notes/save", {
//                         method: "POST",
//                         body: JSON.stringify(taskDto),
//                         headers: {
//                                 [csrfHeaderName]: csrfHeaderToken
//                         }
//                         // headers: {
//                         //         'Content-type': 'application/json',
//                         //         [csrfHeaderName]: csrfHeaderToken
//                         // }
//                 }).then((resp) => resp.json());
// }

// function onClickAdd() {
//     let id = document.getElementById('taskId').getAttribute('value');
//
//     let tableBody = document.querySelector(".table-body");
//
//     fetch(`http://localhost:8080/notes/${id}`)
//         .then(response => response.json())
//         .then(task => {
//
//                 //smth like load todos after that adding the new
//
//             // let tableRow = document.createElement('tr');
//             //
//             // let descriptionTd = document.createElement('td');
//             // descriptionTd.textContent=task.description;
//             //
//             // let doneTd = document.createElement('td');
//             // doneTd.classList.add("done");
//             //
//             // let aDone=document.createElement("a");
//             // aDone.href="#";
//             // aDone.title="Done";
//             //
//             // const svgDone = document. createElementNS("http://www.w3.org/2000/svg", "svg");
//             // svgDone.setAttribute ("width", "14" );
//             // svgDone.setAttribute ("height", "16" );
//             // svgDone.setAttribute("viewBox", "0 0 448 512");
//             //
//             //
//             // let path =document.createElement("path");
//             // path.d="M438.6 105.4c12.5 12.5 12.5 32.8 0 45.3l-256 256c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0L160 338.7 393.4 105.4c12.5-12.5 32.8-12.5 45.3 0z";
//             //
//             // svgDone.appendChild(path);
//             // aDone.appendChild(svgDone);
//             // doneTd.appendChild(aDone);
//             //
//             //
//             // let removeTD = document.createElement('td');
//             // removeTD.classList.add("remove");
//             //
//             // let aRemove=document.createElement("a");
//             // aRemove.href="#";
//             // aRemove.title="Remove";
//             //
//             // const avgRemove = document. createElementNS("http://www.w3.org/2000/svg", "svg");
//             // avgRemove.setAttribute ("width", "14" );
//             // avgRemove.setAttribute ("height", "16" );
//             // avgRemove.setAttribute("viewBox", "0 0 448 512");
//             //
//             //
//             // let pathRemove =document.createElement("path");
//             // pathRemove.d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z";
//             //
//             // avgRemove.appendChild(pathRemove);
//             // aRemove.appendChild(avgRemove);
//             // removeTD.appendChild(aRemove);
//             //
//             // tableRow.appendChild(descriptionTd);
//             // tableRow.appendChild(doneTd);
//             // tableRow.appendChild(removeTD);
//             //
//             // tableBody.appendChild(tableRow);
//         })
//
// }