# Who:  Juan Vera
# What: single_line.asm
# Why:  Assignment 1:  
	# Learning how to print characters and use a basic array
	# to store user inputs and output them in an orderly fashion
# When: Created Feb 25, 2019
	# Due March 3, 2019
# How:  $t0 stores array address, and is the array index pointer
	# $t1 is used as a MAX address to stop array indexing loop
	# $t2 is used to compare $t0 and $t1 and store input/output of loop
	# using a while loop, we iterated through an array, adding and then
	# printing during the second iteration of the loop.


.data
	array:			.space			80
	array_size:		.word			20
	prompt:			.asciiz			"Enter an int: "
	
.text
.globl main

main: #program entry

	la $t0, array 		#set array size limit on array
	la $t1, array_size
	lw $t1, 0($t1)
	sll $t1, $t1, 2
	addu $t1, $t0, $t1	#t0 has array address, t1 has address + array_size
	
	input_loop: 
    	slt $t2, $t0, $t1    	#check if array is less than size
    	beq $t2, $0, exit_input_loop

	la $t2, prompt 		# prompt for int input
    	move $a0, $t2
    	li $v0, 4
    	syscall
    	li $v0, 5   		# get input
    	syscall
	sw $v0, 0($t0)		#store input into array
    	addiu $t0, $t0, 4	#increment array index
	
	j input_loop
		
	exit_input_loop:

	la $t0, array 		#reset array to pre-index register num
	
	output_loop:
	slt $t2, $t0, $t1 	#less than check
	beq $t2, $0, exit_output_loop
	
	li $v0, 1 		#print int
	lw $t2 0($t0)
	move $a0, $t2
	syscall
	
	li $a0, ' '		#space
	li $v0, 11
	syscall
	
	addiu $t0, $t0, 4	#index++
	
	j output_loop
	
	exit_output_loop:
	
	exit:
	li $v0, 10
	syscall
