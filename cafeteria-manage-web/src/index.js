(async () => {

    const helloUrl = "http://localhost:8080/hello";
    const menuPlanUrl = "http://localhost:8080/workDay";

    const element = document.getElementById("app");
    const menuPlanResponse = await fetch(menuPlanUrl);
    const menuPlanDatas = await menuPlanResponse.json();

    // rendering 될 내용
    let contents = `
        <table id="t01">
            <tr>
                ${menuPlanDatas.map(menuPlanData => `
                    <th>${menuPlanData.date}</th>
                `).join("")}
            </tr>
            <tr>
                ${menuPlanDatas.map(menuPlanData => ` 
                    <td>
                        ${menuPlanData.menus.map(menu => `
                            <p>${menu.name}</p>
                        `).join("")}
                    </td> 
                `).join("")}
            </tr>
        </table>`;

    element.innerHTML = contents;
})();