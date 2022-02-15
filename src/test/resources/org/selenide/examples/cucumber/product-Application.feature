Feature: Product Application

  
 Scenario: To set Up data in application
  
    Given Open browser with "http://localhost:8080/"
    When Click on Add New Product
    Then Enter product Details Name , Price , Description "Toy" "90000" "Baby Toy"
  
  Scenario: User can preview all products

    Given Open browser with "http://localhost:8080/"
    When Click on Available Product button to view all products available
    Then Verify on page Available Products
    Then Verify on page Available Products Add New Product Button available


  Scenario: User can preview single product

    Given Open browser with "http://localhost:8080/"
    When Click on Available Product button to view all products available
    Then Verify on page Available Products
    Then Verify product number "1"
    Then Verify you can view single product
    Then Verify on page Single Product detail have button To Product list
    
    
  Scenario: User can add product
  
    Given Open browser with "http://localhost:8080/"
    When Click on Add New Product
    Then Enter product Details Name , Price , Description "Watch" "30000" "Analog Watch"
    
   
  Scenario: User can edit Product
  
    Given Open browser with "http://localhost:8080/"
    When Click on Available Product button to view all products available
    Then Edit product item number "1"
    Then Edit price of product to "4000"
    
   Scenario: User can delete product
  
    Given Open browser with "http://localhost:8080/"
    When Click on Available Product button to view all products available
    Then Delete product item number "2"