(() => {
    const inputmemberNo = document.querySelector('#inputmemberNo');
    const inputmemberAccount = document.querySelector('#inputmemberAccount');
    const inputmemberName = document.querySelector('#inputmemberName');
    const inputmemberGender = document.querySelector('#inputmemberGender');
    const inputmemberPhone = document.querySelector('#inputmemberPhone');
    const inputmemberEmail = document.querySelector('#inputmemberEmail');
    const inputmemberAddress = document.querySelector('#inputmemberAddress');
    const inputmemberJoinTime = document.querySelector('#inputmemberJoinTime');
    const inputlevelNo = document.querySelector('#inputlevelNo');
    const inputmemberBirthday = document.querySelector('#inputmemberBirthday');
    const inputmemberNation = document.querySelector('#inputmemberNation');
    const inputmemberPic = document.querySelector('#inputmemberPic');
    const inputmemberCard = document.querySelector('#inputmemberCard');
    const inputmemberPoints = document.querySelector('#inputmemberPoints');
    const inputmemberStat = document.querySelector('#inputmemberStat');
    const inputs = document.querySelectorAll('input');
    const confirmbtn = document.querySelector('#confirmbtn');
    const avatarpreview = document.querySelector('#avatar-preview')
    const avatarUpload = document.getElementById("inputmemberPic");
    const avatarPlaceholder = document.getElementById("avatar-placeholder");
    const avatarPreview = document.getElementById("avatar-preview");


    const canslebtn = document.querySelector('#canslebtn');
    const confirmbtn2 = document.querySelector('#confirmbtn2');
    const cancelbtn2 = document.querySelector('#cancelbtn2');
    const msg = document.querySelector('#msg');
    const memberId = sessionStorage.getItem('memberNo');
    console.log(memberId);
    console.log("register.js啟動");

    // 查資料回來========================================================
    function getmemberdata() {//取瀏覽器的memberid 去查出來
        console.log('進入getmemberdata')
        fetch("getmemberdata", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                memberNo: memberId,
            }),
        })
            .then((response) => response.json())
            .then(body => {
                // 在這裡處理回應的資料
                const {
                    memberNo,
                    memberAccount,
                    memberName,
                    memberGender,
                    memberPhone,
                    memberEmail,
                    memberAddress,
                    memberJoinTime,
                    levelNo,
                    memberBirthday,
                    memberNation,
                    memberPic4json,
                    memberCard,
                    memberPoints,
                    memberStat
                } = body;
                inputmemberNo.value = memberNo;
                inputmemberAccount.value = memberAccount;
                inputmemberName.value = memberName;
                let memberGendera = '';
                if (memberGender === 2) {
                    memberGendera = '女生';
                } else if (memberGender === 1) {
                    memberGendera = '男生'
                }
                inputmemberGender.value = memberGendera;
                inputmemberPhone.value = memberPhone;
                inputmemberEmail.value = memberEmail;
                inputmemberAddress.value = memberAddress;
                inputmemberJoinTime.value = memberJoinTime;
                inputlevelNo.value = levelNo;
                inputmemberBirthday.value = memberBirthday;
                inputmemberNation.value = memberNation;
                // avatarpreview.src = memberPic4json;
                inputmemberCard.value = memberCard;
                inputmemberPoints.value = memberPoints;
                inputmemberStat.value = memberStat;
                inputmemberAccount.value = memberAccount;

                avatarpreview.src = `http://localhost:8080/hibernate_war/DBGifReaderController?memberNo=${memberNo}`;
                if(memberPic4json){
                    avatarPreview.style.display = "block";
                    avatarPlaceholder.style.display = "none";
                }else {
                    avatarPreview.src = "#";
                    avatarPreview.style.display = "none";
                    avatarPlaceholder.style.display = "block";
                }


            })
            .catch((error) => {
                // 處理錯誤
                console.error("Error:", error);
            });

    };
    //============總之先查一次===============================
    getmemberdata();

// 性別input控制=====================================================================
    const selectGender = document.querySelector('#selectGender');
    selectGender.addEventListener('change', (e) => {
        const selectedValue = selectGender.value;
        // console.log(selectGender.value);
        inputmemberGender.value = selectedValue;
    })
    //重製按鈕===========================================================================
    canslebtn.addEventListener('click', () => {
        getmemberdata();
    });

    //讀取圖片 轉成base64==================================================
    let base64Image = '';
    inputmemberPic.addEventListener("change", function (event) {
        const file = event.target.files[0]; // 獲取選擇的檔案
        if (file) {
            const reader = new FileReader(); //讀取
            reader.onload = function (e) {
                const imageSrc = e.target.result; // 獲取數據
                base64Image = imageSrc.split(",")[1];// 轉成base64

            };
            reader.readAsDataURL(file); // 讀取成url
        }
    });


// 確認修改按鈕=================================================================
    confirmbtn.addEventListener('click', () => {
        // 前端確認資料填寫
        msg.textContent = ' ';
        console.log("按鈕啟動");
        const nicknameLength = inputmemberName.value.length;
        if (nicknameLength < 1 || nicknameLength > 20) {
            msg.textContent = '姓名長度須介於1~20字元';
            return;
        }
        const phoneValue = inputmemberPhone.value;
        const phonePattern = /^09\d{8}$/;
        if (!phonePattern.test(phoneValue)) {
            msg.textContent = '電話號碼須為09開頭，且為十碼'
            return;
        }
        const emailValue = inputmemberEmail.value;
        const emailPattern = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
        if (!emailPattern.test(emailValue)) {
            msg.textContent = '請輸入電子信箱格式'
            return;
        }

        // console.log(inputmemberAddress.value);
        // const AddressValue = inputmemberAddress.value;
        // const AddressPattern = /^[\u4E00-\u9FA5]{3,}(?:市|縣|區)[^\s]*[路街巷][\u4E00-\u9FA5]{2,}/;
        // if (!AddressPattern.test(AddressValue)) {
        //     msg.textContent = '請輸入地址，其中需包含:縣/市/區;路/街/巷'
        //     return;
        // }

        const memberCardLength = inputmemberCard.value.length;
        if (memberCardLength < 15 || memberCardLength > 19) {
            msg.textContent = '信用卡長度須介於15~19碼';
            return;
        }
        let membergender = inputmemberGender.value;
        if (membergender ==="男生") {
            membergender=1;
        }else if(membergender ==="女生"){
            membergender=2;
        }
        // 檢查結束
        console.log(inputmemberNo.value);
        console.log(base64Image);

        msg.textContent = '';
        fetch('edit', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                memberNo: inputmemberNo.value,
                memberAccount: inputmemberAccount.value,
                memberName: inputmemberName.value,
                memberPhone: inputmemberPhone.value,
                memberEmail: inputmemberEmail.value,
                memberName: inputmemberName.value,
                memberGender: membergender,
                memberPhone: inputmemberPhone.value,
                memberEmail: inputmemberEmail.value,
                memberAddress: inputmemberAddress.value,
                memberBirthday: inputmemberBirthday.value,
                memberNation: inputmemberNation.value,
                memberPic4json: base64Image,
                memberCard: inputmemberCard.value,
            }),
        })
            .then(resp => resp.json())// .then(function (resp) {resp.json();)})
            .then(body => {
                console.log(body);
                const {successful} = body;//const successful = body.successful;
                const {memberAccount} = body;
                if (successful) {
                    // for (let input of inputs) {
                    //     input.disabled = true;
                    // }
                    msg.className = 'info';
                    msg.textContent = '修改成功';
                } else {
                    msg.className = 'error';
                    msg.textContent = '修改失敗';
                }
            });

    });


})();