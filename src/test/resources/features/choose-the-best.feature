Feature:
  Support for Choose the best type of questions

  Scenario Outline: Creation of Choose the best Question

    Given "tom" has created a practice "Practice 1"
    And creates a question of type "Choose the best"
    And provides choices as answer

    Examples:
      | Choice 1 |
      | Choice 2 |
      | Choice 3 |
      | Choice 4 |

    And select answer as "Choice 3"
    Then The question should be added to the practice

  Scenario: Answering a Choose the best question
    Given "jerry" attempts the practice
    And answers a choose the best question
    When selects the correct Choice
    Then Prompt as correct answer

    When selects the wrong Choice
    Then Prompt as wrong answer
