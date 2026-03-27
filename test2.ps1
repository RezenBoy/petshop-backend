try {
    $regResponse = Invoke-RestMethod -Uri 'http://localhost:8080/api/users/register' -Method Post -Body '{"fullName":"Test","password":"password123","usersCategory":"USER","addressEmbeddable":{"email":"t999_639102427007462973@t.com"}}' -ContentType 'application/json'
    Write-Host "Reg: $($regResponse | ConvertTo-Json -Depth 5)"
    $email = $regResponse.addressEmbeddable.email

    $login = Invoke-RestMethod -Uri 'http://localhost:8080/api/users/login' -Method Post -Body "{"email":"","password":"password123"}" -ContentType 'application/json'
    $token = $login.token
    Write-Host "Login successful. Token length: $($token.Length)"

    $headers = @{"Authorization" = "Bearer $token"}

    $checkoutBody = '{"paymentMode":"CASH","cartItems":[{"productId":1,"quantity":1}]}'
    $check = Invoke-RestMethod -Uri 'http://localhost:8080/api/user/orders/checkout' -Method Post -Body $checkoutBody -ContentType 'application/json' -Headers $headers
    Write-Host "Checkout: $check"

    $orders = Invoke-RestMethod -Uri 'http://localhost:8080/api/user/orders' -Method Get -Headers $headers
    Write-Host "Orders JSON:"
    $orders | ConvertTo-Json -Depth 5
} catch {
    Write-Host "Error: $($_.Exception.Message)"
    if ($_.ErrorDetails) {
        Write-Host "Details: $($_.ErrorDetails.Message)"
    }
}
