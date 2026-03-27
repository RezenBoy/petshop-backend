async function run() {
    const email = 't1006@t.com';
    const regRes = await fetch('http://localhost:8080/api/users/register', {
        method: 'POST', headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({fullName:'Test', password:'password123', usersCategory:'USER', addressEmbeddable:{email}})
    });
    console.log('Reg status:', regRes.status);
    console.log('Reg text:', await regRes.text());
    
    if (regRes.status !== 200) return;
    
    const loginRes = await fetch('http://localhost:8080/api/users/login', {
        method: 'POST', headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({email, password:'password123'})
    });
    console.log('Login status:', loginRes.status);
    const loginData = await loginRes.json();
    const token = loginData.token;
    
    const checkoutRes = await fetch('http://localhost:8080/api/user/orders/checkout', {
        method: 'POST', headers: {'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token},
        body: JSON.stringify({paymentMode:'CASH', cartItems:[{productId:1, quantity:1}]})
    });
    console.log('Checkout text:', await checkoutRes.text());
    
    const ordersRes = await fetch('http://localhost:8080/api/user/orders', {
        headers: {'Authorization': 'Bearer ' + token}
    });
    console.log('Orders status:', ordersRes.status);
    console.log('ORDERS ERROR JSON:', await ordersRes.text());
}
run();
