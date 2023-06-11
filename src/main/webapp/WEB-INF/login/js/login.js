(function () {
    const memberAccount = document.querySelector('#memberAccount');
    const password = document.querySelector('#password');
    const errMsg = document.querySelector('#errMsg');

    document.getElementById('btn1').addEventListener('click', function () {
        fetch('login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                memberAccount: memberAccount.value,
                password: password.value
            }),
        })
            .then(resp => resp.json())
            .then(body => {
            errMsg.textContent = '';
            const { successful, message } = body;
            if (successful) {
                const { memberNo, memberAccount } = body;
                sessionStorage.setItem('memberNo', memberNo);
                sessionStorage.setItem('memberAccount', memberAccount);
                // sessionStorage.setItem('roleId', roleId);
                location = '../../index.html';
            } else {
                errMsg.textContent = message;
            }
        });
    });
})();