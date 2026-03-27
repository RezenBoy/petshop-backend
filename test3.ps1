try {
    $regResponse = Invoke-RestMethod -Uri 'http://localhost:8080/api/users/register' -Method Post -Body '{"fullName":"Test","password":"password123","usersCategory":"USER","addressEmbeddable":{"email":"t1004@t.com"}}' -ContentType 'application/json'
    $login = Invoke-RestMethod -Uri 'http://localhost:8080/api/users/login' -Method Post -Body '{"email":"t1004@t.com","password":"password123"}' -ContentType 'application/json'
    $token = $login.token
    $headers = @{"Authorization" = "Bearer $token"}
    $checkoutBody = '{"paymentMode":"CASH","cartItems":[{"productId":1,"quantity":1}]}'
    $check = Invoke-RestMethod -Uri 'http://localhost:8080/api/user/orders/checkout' -Method Post -Body $checkoutBody -ContentType 'application/json' -Headers $headers
    $orders = Invoke-RestMethod -Uri 'http://localhost:8080/api/user/orders' -Method Get -Headers $headers -ErrorAction Stop
} catch {
    Write-Host "Error: $($_.Exception.Message)"
    if ($_.ErrorDetails) {
        Write-Host "Details: $($_.ErrorDetails.Message)"
    }
}
