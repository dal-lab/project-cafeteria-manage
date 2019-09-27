(async () => {

    const helloUrl = "http://localhost:8080/hello";

    const element = document.getElementById("app");
    const helloResponse = await fetch(helloUrl);
    let value = await helloResponse.text();

    // rendering 될 내용
    let contents = `
        <p>
            ${value}
        </p>
    `;

    element.innerHTML = contents;
})();