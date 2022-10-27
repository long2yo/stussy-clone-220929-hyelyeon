const registerButton = document.querySelector(".login-button");
const registerInputs = document.querySelectorAll(".login-input");

//enter누르면 회원가입 되기!
for(let i = 0; i < registerInputs.length; i++) {
    registerInputs[i].onkeyup = () => {
            if(window.event.keyCode == 13){
                if(i != 3){
                    registerInputs[i + 1].focus();
                }else{
                    registerButton.click();
                }
            }
    }
}
//submit버튼이면 이렇게 안써도 enter가 먹힌다.


registerButton.onclick = () => {
    
    let registerInfo = {
        lastName: registerInputs[0].value,
        firstName: registerInputs[1].value,
        email: registerInputs[2].value,
        password: registerInputs[3].value
    }

    $.ajax({
        async:false,
        type:"post",
        url:"/api/account/register",
        contentType:"application/json",
        data: JSON.stringify(registerInfo),   // JSON.stringify :  obj-> json 으로 바꿔줌
        dataType: "json",
        success:(response) => {
            location.replace("/account/login");
        },
        error:(error) => {
            console.log(error);
            validationError(error.responseJSON.data);
        }

    });
}

function validationError(error) {
    const accountErrors = document.querySelector(".account-errors");
    const accountErrorList = accountErrors.querySelector("ul");

    const errorValues = Object.values(error);

    accountErrorList.innerHTML = "";

    errorValues.forEach((value) => {
        accountErrorList.innerHTML += `
            <li>${value}</li>
        `;

    });

    accountErrors.classList.remove("errors-invisible");
}