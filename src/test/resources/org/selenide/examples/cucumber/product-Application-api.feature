
@API
Feature: Validate api of product application

  @allProducts
  Scenario: Get all Products
    Given User generates endpoint
    When User hit the engagement get API with resourse v1/products
    Then Validate status code is 200
    
  @getSingleProduct
  Scenario: Get Single Products
    Given User generates endpoint
    When User hit the engagement get API with resourse v1/products/1
    Then Validate status code is 200
    
  @postSingleProduct
  Scenario: Add SIngle Product
    Given User generates endpoint
    When User hit the engagement post API with resourse v1/products/
    Then Validate status code is 201
   
  @deleteSingleProduct
  Scenario: Delete Single Product
    Given User generates endpoint
    When User hit the engagement delete API with resourse v1/products/3
    Then Validate status code is 204
    