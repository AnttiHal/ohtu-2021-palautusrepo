Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  username "antti" and password "oiueroit34534" are entered
        Then  system will respond with "new user registered"
    
    
    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When  username "pekka" and password "aksdjfkahsd2343" are entered
        Then  system will respond with "new user not registered" 
    
    
    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  username "an" and password "aksdjfkahsd2343" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When  username "tyuweruyt" and password "ak" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When  username "tyuweruyt" and password "akjshdfgjashgfdsa" are entered
        Then  system will respond with "new user not registered"
    
    Scenario: can login with successfully generated account
        Given command new is selected
        And username "eero" and password "salainen1" are entered
        When   command login is selected
        And  username "eero" and password "salainen1" are entered
        Then  system will respond with "logged in"