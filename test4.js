async function run() {
    const email = 'test_' + Date.now() + '@t.com';
    await fetch('http://localhost:8080/api/users/register', {
        method: 'POST', headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({fullName:'Test', password:'123', usersCategory:'USER', addressEmbeddable:{email}})
    });
    const loginRes = await fetch('http://localhost:8080/api/users/login', {
        method: 'POST', headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({email, password:'123'})
    });
    const loginData = await loginRes.json();
    const token = loginData.token;
    
    await fetch('http://localhost:8080/api/user/orders/checkout', {
        method: 'POST', headers: {'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token},
        body: JSON.stringify({paymentMode:'CASH', cartItems:[{productId:1, quantity:1}]})
    });
    
    const ordersRes = await fetch('http://localhost:8080/api/user/orders', {
        headers: {'Authorization': 'Bearer ' + token}
    });
    const ordersText = await ordersRes.text();
    const fs = require('fs');
    fs.writeFileSync('out.txt', ordersText);
}
run();
