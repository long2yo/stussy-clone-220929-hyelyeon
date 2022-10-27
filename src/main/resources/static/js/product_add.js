const fileAddButton = document.querySelector(".add-button");
const fileInput = document.querySelector(".file-input");
const submitButton = document.querySelector(".submit-button");

let productImageFiles = new Array();

fileAddButton.onclick = () => {
    fileInput.click();
}

fileInput.onchange = () => {
    const formData = new FormData(document.querySelector("form"));

    formData.forEach((value) => {
        if(value.size != 0){
            productImageFiles.push(value);
            console.log(productImageFiles);

            getImgePreview();

            // 연달아서 같은 파일 올리기 : fileInput이 value를 기억하고 있어서 이거 안적으면 안됨
            fileInput.value = null;

        }


    });
}

function getImgePreview() {
    const productImages = document.querySelector(".product-images");

    productImages.innerHTML = "";


    //i 추가 : 사진 추가 안될 때
    productImageFiles.forEach((file, i) => {
        const reader = new FileReader();

        reader.onload = (e) => {
            productImages.innerHTML += `
            <div class="img-box">
                <i class="fa-solid fa-xmark"></i>
                <img class="product-img" src="${e.target.result}">
            </div>
            
            `;

            const deleteButton = document.querySelectorAll(".fa-xmark")
            deleteButton.forEach((xbutton, index) => {
                xbutton.onclick = () => {
                    if(confirm("상품 이미지를 지우시겠습니까?")){
                        productImageFiles.splice(index, 1);

                        //안될 때
                        console.log(productImageFiles);

                        getImgePreview();
                    }
                };

            })



        }

        //사진 추가 안될때
       setTimeout(() => {
        reader.readAsDataURL(file)}, i * 100);      

    });

}