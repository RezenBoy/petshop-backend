try {
    $regResponse = Invoke-RestMethod -Uri 'http://localhost:8080/api/users/register' -Method Post -Body '{"fullName":"Test","password":"123","usersCategory":"USER","addressEmbeddable":{"email":"t1@t.com"}}' -ContentType 'application/json'
} catch {
    Write-Host "Reg Error: $($_.ErrorDetails.Message)"
}
try {
    $login = Invoke-RestMethod -Uri 'http://localhost:8080/api/users/login' -Method Post -Body '{"email":"t1@t.com","password":"123"}' -ContentType 'application/json'
    $token = $login.token

    $headers = @{"Authorization" = "Bearer $token"}

    $checkoutBody = '{"paymentMode":"CASH","cartItems":[{"productId":1,"quantity":1}]}'
    $check = Invoke-RestMethod -Uri 'http://localhost:8080/api/user/orders/checkout' -Method Post -Body $checkoutBody -ContentType 'application/json' -Headers $headers
    Write-Host "Checkout: $check"

    $orders = Invoke-RestMethod -Uri 'http://localhost:8080/api/user/orders' -Method Get -Headers $headers
    Write-Host "Orders JSON:"
    $orders | ConvertTo-Json -Depth 5
} catch {
    Write-Host "Error: $($_.ErrorDetails.Message)"
}
