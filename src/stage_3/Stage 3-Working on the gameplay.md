## Work on project. Stage 3/5:Working on the gameplay

Project: [Last Pencil (Java)](https://hyperskill.org/projects/341)

## Working on the gameplay

## Description

An interesting idea has come to your mind. Let's change the rules of game. Players take turns taking `X` pencils until none of them remain.

## Objectives

Expand your program by creating a loop. Each player takes turns removing pencils until 0 pencils remain on the table. Each iteration prints 2 lines: lines with pencils (vertical bars) and whose turn is now by printing the `*NameX*'s turn` string. If there are no pencils left after a player's move, the loop breaks and the game ends without any additional output. 

## Examples

The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

**Example 1:**

```no-highlight
How many pencils would you like to use:
> 5
Who will be the first (John, Jack):
> John
|||||
John's turn:
> 2
|||
Jack's turn:
> 1
||
John's turn:
> 2
```

**Example 2:**

```no-highlight
How many pencils would you like to use:
> 15
Who will be the first (John, Jack):
> John
|||||||||||||||
John's turn:
> 8
|||||||
Jack's turn:
> 7
```