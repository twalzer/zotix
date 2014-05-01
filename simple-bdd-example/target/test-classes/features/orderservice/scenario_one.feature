Feature: Order Service
  In order to purchase an item
  As a customer


Scenario: Free shipping a month before Christmas
  Given a customer that Christmas is celebrated 24 of December
  When a customer buys a book on 2012-12-10
  Then the shipping cost should be 0 euro