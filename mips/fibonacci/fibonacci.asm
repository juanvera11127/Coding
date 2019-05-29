# Who:  Juan Vera
# What: fibonacci.asm
# Why:  A program to create an array with the fibonacci sequence,
	#test input validation, and find the limits of 32 bits.
# When: Created 3/7/19, Due 3/17/19
# How:  $t0 and $t1 hold a and b for fibonacci calculations
	#$t2 and $t3 are used as temporary address registers, but 
	#later used for array and array size value holders
	#$t4 is used as a temp value holder

.data
	prompt:			.asciiz			"Enter an int (n < 48): "
	answer:			.asciiz			"\nnth fibonacci number: "
	big_error:		.asciiz			"Error: input too big!\n"
	negative_error:		.asciiz			"Error: input can't be negative\n"
	.align 2
	array:			.space			192
	size:			.word			48
	
.text
.globl main

main: #program entry

	li $t0, 0			#t0 = a
	li $t1, 1			#t1 = b
	
	start:
	la $t2, prompt
	move $a0, $t2		
	li $v0, 4			#prompt input
	syscall
	
	
	li $v0, 5			#get input
	syscall
	
	
	move $t2, $v0			#store input in t2
	
	slti $t3, $t2, 48
	beq $0, $t3, validation_1		#check if n < max factorial
	
	blt $t2, 0, validation_2
	
	j continue
	
	validation_1:
	la $t3, big_error
	move $a0, $t3		
	li $v0, 4			#error prompt
	syscall
	
	j start				#start over
	
	validation_2:
	la $t3, negative_error
	move $a0, $t3		
	li $v0, 4			#error prompt
	syscall
	
	j start				#start over
	
	
	
	continue:			#input validated: continue
	sll $t2, $t2, 2

	
	la $t3, array			#t3 = array
	addu $t2, $t3, $t2		#t2 = array + n
	
	input_loop:
	bgt $t3, $t2, exit_input_loop
	
	sw $t0, 0($t3)
	addu $t4, $0, $t1
	addu $t1, $t1, $t0		#fib calculations
	addu $t0, $0, $t4
	
	addu $t3, $t3, 4		#increment index
	j input_loop
		
	exit_input_loop:
	
	li $v0, 4
	la $t0, answer
	move $a0, $t0
	syscall
	
	addiu $t3, $t3, -4		#get last index
	
	li $v0, 36
	lw $t4 0($t3)			#print nth fib num
	move $a0, $t4
	syscall
	
	li $a0, '\n'
	li $v0, 11			#new line
	syscall
	
	la $t3, array			#reset array to pre-index register num
	
	output_loop:			#prints every number in array
	bgt $t3, $t2, exit_output_loop
	
	li $v0, 36		
	lw $t4 0($t3)			#print int
	move $a0, $t4
	syscall
	
	li $a0, ' '
	li $v0, 11			#print space
	syscall
	
	addiu $t3, $t3, 4		#increment
	
	j output_loop
	
	exit_output_loop:
	
	exit:
	li $v0, 10
	syscall
