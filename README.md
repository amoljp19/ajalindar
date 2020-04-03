The deliverable of the exercise is a *Pull Request*. Each step should be in a single commit so that we can track the changes.

Read the whole document before starting the exercise. The timing of each task should give you a hint of what we expect

Keep in mind that we are not looking for extra complex solutions. We are more interested in a well organized, maintainable, simple, testable code.

# Kolibree Android coding exercise

This application wants to implement a multithreaded sequential counter, but something's not working right. If you run the app, you can notice some issues

- It prints the same number multiple times
- The countdown timer finishes, but for some reason it keeps on printing numbers

## 1. Fix the issues

What's expected from this step?
- Fix same number printed multiple times
- Fix numbers printed after countdown finishes
- Fix lifecycle issues, if any
- Fix memory leaks, if any
- Limit variable scope, if needed
- Add comments for each decision

_5-20 minutes_

## 2. Improve the code
This code was created by a junior developer. Can you improve its maintainability? 

You have absolute freedom, but add comments to explain your decisions.

Examples of improvements in this step
- Reorganize code/classes/files
- Refactor code
- Use libraries that will improve maintanability
- Unit tests

_20-60 minutes_

## 3. Improve the layout
Our CEO is concerned that the current UI won't catch the user's eye. He says he wants the reset button and the timer displayed at the bottom, but he's uncertain about the final looks

You have absolute freedom

_5-15 minutes_

## 4. Answer to the following questions

Copy the following questions to a new document _ANSWERS.md_ and answer to them. Please don't answer in 2 lines, we'd like to see somewhat detailed responses.

1. Keeping the original structure, briefly explain other ways to avoid printing the same number twice.
3. Our QA team said something about automated testing. How would you satisfy their requirements? What issues do you expect?
2. If this was a long term project, detail your ideal app structure.
3. What if you had to send the counter values to a REST remote service?  

_15-25 minutes_
