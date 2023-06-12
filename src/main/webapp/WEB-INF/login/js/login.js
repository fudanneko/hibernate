( ()=> {
    const memberAccount = document.querySelector('#memberAccount');
    const password = document.querySelector('#password');
    const errMsg = document.querySelector('#errMsg');
    const btn1 = document.querySelector('#btn1');
    console.log("login.js啟動");
    btn1.addEventListener('click', function () {
        console.log(memberAccount.value);
        console.log(password.value);
        fetch('login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                memberAccount: memberAccount.value,
                memberPassword: password.value
            }),
        })

            .then(resp => resp.json())
            .then(body => {
                errMsg.textContent = '';
                const {successful, message} = body;
                if (successful) {
                    const {memberNo, memberAccount} = body;
                    sessionStorage.setItem('memberNo', memberNo);
                    sessionStorage.setItem('memberAccount', memberAccount);
                    // sessionStorage.setItem('roleId', roleId);
                    location = '../login/login.html';
                } else {
                    errMsg.textContent = message;
                }
            });
    });
})();