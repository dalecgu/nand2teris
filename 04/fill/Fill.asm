// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed.
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.
(BEGIN)

@KBD
D=M
@BLACKEN
D;JNE

@status
M=0         // status = 0

@CLEAR
0;JMP

(BLACKEN)
@status
M=-1        // status = -1

(CLEAR)
@8192
D=A
@c
M=D         // c = 512

@i
M=0         // i = 0
(LOOP)
@c
D=M
@i
D=D-M
@END
D;JEQ       // c - i == 0; goto END
@SCREEN
D=A
@i
D=D+M
@temp
M=D
@status
D=M
@temp
A=M
M=D         // blacken
@i
M=M+1       // i = i + 1
@c
D=M
@i
D=D-M
@LOOP
D;JGT       // c - i > 0; goto LOOP
(END)

@BEGIN
0;JMP
