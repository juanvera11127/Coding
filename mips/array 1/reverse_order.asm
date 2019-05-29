# Who:  Juan Vera
# What: reverse_order.asm
# Why:  Assignment 1:  
	# Learn how to print from an array in reverse order, then
	# print n numbers per line from the array
# When: Created Feb 25, 2019
	# Due March 3, 2019
# How:  $t0 stores array address, and is the array index pointer
	# $t1 is used as a MAX and MIN address to stop array indexing loop
	# $t2 is used to compare $t0 and $t1 and store input/output of loop
	# $t3 and $t4 store the n value and counter respectively
	# using a while loop, we iterated through an array, adding and then
	# printing from array + size to array addresses
	# Then, we had a loop to print from array to array size addresses, but
	# after n iterations, jump to a different loop for a new line, then
	# jump back to the previous loop

.data
	array:			.space			80
	array_size:		.word			20
	prompt:			.asciiz			"Enter an int: "
	.align 2
	factor:			.asciiz			"Enter a divide factor: n <= 20: "
	.align 2
	
	
.text
.globl main

main: #program entry

	la $t0, array			#set array size limit on array
	la $t1, array_size
	lw $t1, 0($t1)
	sll $t1, $t1, 2
	addu $t1, $t0, $t1		#t0 has array address, t1 has address + array_size

	
	input_loop:
    	slt $t2, $t0, $t1	    	#check if array is less than size else exist
    	beq $t2, $0, exit_input_loop

	la $t2, prompt			# prompt for int input
    	move $a0, $t2
    	li $v0, 4
    	syscall
    	li $v0, 5		   	# get input
    	syscall				
    	sw $v0, 0($t0)			#store input into array

    	addiu $t0, $t0, 4		#increment array index
	
	j input_loop
		
	exit_input_loop:
	la $t1, array			#min pointer set to array 
	
	output_loop:
	slt $t2, $t1, $t0		#check if less than
	beq $t2, $0, exit_output_loop
	
	li $v0, 1
	lw $t2 -4($t0)			#print int
	move $a0, $t2
	syscall
	
	li $a0, ' '
	li $v0, 11			#print space
	syscall
	
	addi $t0, $t0, -4		#decrement index
	
	j output_loop
	
	exit_output_loop:
	
	la $t0, array		
	la $t1, array_size		
	lw $t1, 0($t1)
	sll $t1, $t1, 2			#set t1 to MAX
	addu $t1, $t0, $t1

	li $a0, '\n'			#new line print
	li $v0, 11
	syscall

	la $t2, factor			#print factor string
	li $v0, 4
	move $a0, $t2
	syscall
	
    	li $v0, 5			#get factor int
    	syscall
    	
    	move $t3, $v0		    	#set counter for division loop
    	move $t4, $0
    	    		  		
	divide_loop:
    	slt $t2, $t0, $t1		#check if less than
	beq $t2, $0, exit_divide_loop
	beq $t3, $t4, new_line_loop	#check if counter == division num
	
	li $v0, 1
	lw $t2 0($t0)			#print int
	move $a0, $t2
	syscall
	
	li $a0, ' '
	li $v0, 11			#print space
	syscall
	
	addiu $t0, $t0, 4		#incrememt
	addiu $t4, $t4, 1
	
	j divide_loop
	
	new_line_loop:

	li $a0, '\n'			#print space
	li $v0, 11
	syscall

	move $t4, $0			#reset counter
	
	j divide_loop
	exit_divide_loop:
	
	exit:
	li $v0, 10
	syscall
