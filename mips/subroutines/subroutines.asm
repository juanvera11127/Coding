# Who:  Juan Vera
# What: subroutines.asm
# Why:  A program that works with subroutines in order to
	# sort an input into an ascending array and 
	# perform binary searches recursively with inputted values on the 
	# same array
# When: Created 3/26/19
	#Due 4/2/19
# How:  $s0 stores the input quantity
	#t0 is used to store the array address/counter
	#t1 is used to store inputs
	#t2 is used as a limit pointer for array size/counter
	
	#Sorting function
		#a0 is the input to be inserted into the array
		#a1 has array address + size
		#a2 is the array address
		#a3 is used to hold comparison values
	#Binary Search
		#a0 is the input to find
		#a1 is the current low index
		#a2 is the current high index
		#a3 is the calculated middle index
		#v1 is the value at the middle index
		#v0 is the result returned at the end

.data
	array:		.space		80
	size:		.word		20
	prompt:		.asciiz		"How many signed integers would you like to enter? "
	int:		.asciiz		"Enter an int: "
	search:		.asciiz		"\nWhat value would you like to search for? "
	true:		.asciiz		"Value found\n"
	false:		.asciiz		"Value not found\n"
	negative_error:	.asciiz		"\nError, value can't be negative\n"
	positive_error:	.asciiz		"\nError, value can't be > 20\n"
	.align 2

.text
.globl main


main:	# program entry

#input validation
validation_loop: 
	#print input prompt
	li $v0, 4
	la $a0, prompt		
	syscall
	
	#get input
	li $v0, 5			
	syscall
	
	#validation checks
	bltz $v0, low_input		#input validation: makes sure input >= 0
	bgt $v0, 20, high_input		#makes sure input <= 20
	
	j continue_validation
	
	low_input:
	#print negative_error
	la $a0, negative_error
	li $v0, 4			
	syscall
	
	j validation_loop		#restart input validation
	
	high_input:	
	#print positive_error
	la $a0, positive_error		
	li $v0, 4			
	syscall
	
	j validation_loop		#restart input validation

	continue_validation:		#validation loop finished
	
	move $s0, $v0			#get input size
	sll $s0, $s0, 2			#convert word input size to bytes
	
	la $a2, array			#load array address
	
	li $t2, 0			#set counter to 0
	
	#print int prompt	
	li $v0, 4
	la $a0, int			
	syscall
	
	#get input	
	li $v0, 5			
	syscall
	
	sw $v0, 0($a2)			#store the first number into array[0] for recursion simplicity
	
	addiu $t2, $t2, 4		#increment counter
	
	
input_loop:
	bgeu $t2, $s0, exit_input_loop	#loop while counter < input size
	
	#prompt next int
	li $v0, 4
	la $a0, int			
	syscall
	
	#get next int
	li $v0, 5			
	syscall
	
	#setup arguments
	move $a0, $v0			#a0 = input number
	addu $a1, $t2, $a2		#a1 = array + size
	
	jal sort_function		#call inputting function
	
	addiu $t2, $t2, 4		#increment counter
	
	j input_loop			#loop
	
	exit_input_loop:
	
	la $t0, array	 		#reset array for counter
		
	addu $t2, $t2, $t0		#set output_loop limit to array + size
	
output_loop:
	bgeu $t0, $t2, exit_output_loop	#loop from array to array + size
	
	#print int
	li $v0, 1 		
	lw $t1 0($t0)			
	move $a0, $t1
	syscall
	
	#print space
	li $a0, ' '			
	li $v0, 11
	syscall
	
	addiu $t0, $t0, 4		#index++
	
	j output_loop			#loop
	
	exit_output_loop:

	
	
BS_loop:
	#prompt search value
	la $a0, search
	li $v0, 4			
	syscall
	
	#get search value
	li $v0, 5
	syscall				
	
	#setup arguments
	move $a0, $v0			#store value as argument
	la $a1, array			#store array as argument
	addu $a2, $a1, $s0		#a2 = array + size
	addiu $a2, $a2, -4		#store array + last index as argument
	
	jal BinarySearch		#call binary search
	
	#check return value
	beqz $v0, BS_false		#check whether BinarySearch returned true or false
	
	#print true
	la $a0, true
	li $v0, 4
	syscall
	
	j BS_loop		#loop
	
	BS_false:
	#print false
	la $a0, false
	li $v0, 4
	syscall
	
	j BS_loop		#loop
	



li $v0, 10			# terminate the program
syscall

#uses recursive binary search to check if an input is in an array
#args:
	#a0 = n to look for
	#a1 = array address + low index
	#a2 = array address + high index
#returns:
	#v0 = 1 if found, 0 if not
.text
BinarySearch:
	
	bgt $a1, $a2, exit_BS		#if low > high: exit
	
	#get middle index
	sub $a3, $a2, $a1		
	srl $a3, $a3, 1			
	
	andi $v1, $a3, 2		#and to cbeck alignment of mid
	
	beq $v1, 2, half_aligned	#align mid if unaligned
	
	continue_BS:
	
	addu $v1, $a1, $a3		#arrray + mid
	lw $v1, 0($v1) 			#get value at mid
	
	beq $a0, $v1, answer		#branch to return if array[mid] == x
	
	#we know we must do recursion, so we allocate and save $ra into stack
	addiu $sp $sp, -4
	sw $ra, 0($sp)
	
	#check which side our value would be in
	bgt $a0, $v1, greater		#greater side
	
	#less than: set range from low to mid - 1
	addu $a2, $a1, $a3
	addiu $a2, $a2, -4
	
	recursion:
	
	jal BinarySearch	#recursive call on size/2
	
	#get ra and deallocate
	lw $ra, 0($sp)
	addiu $sp, $sp, 4
	
	jr $ra			#return

	answer:
	li $v0, 1		#store true as return value
	jr $ra
	
	greater:
	#set range from mid+1 to high
	addu $a1, $a1, $a3
	addiu $a1, $a1, 4
	j recursion
	
	exit_BS:
	li $v0, 0		#store false as return value
	jr $ra

	half_aligned:
	addiu $a3, $a3, 2	#align to the word-ceiling of middle
	j continue_BS
	
	
	
	
	

#inserts an inputted number into the correct spot of a a sorted array
#args:
	#a0 = number to insert
	#a1 = array address + size
	#a2 = array address
#returns:
	#none
.text	
sort_function:
	lw $a3, -4($a1)			#load last index num	
	
	blt $a0, $a3, less_than		#check if num < array[index]
	
	sw $a0, 0($a1)		#if num > array[size-1], its the biggest num
				#store num at array[size]
	jr $ra
	
	less_than:
	#allocate space on sp and save ra for recursion
	addiu $sp, $sp, -4
	sw $ra, 0($sp)
	
	sw $a3, 0($a1)		#store last num in array in array[size]
	
	addiu $a1, $a1, -4	#decrement array pointer for recursion 
	
	blt $a1, $a2, first	#chekcs if we are in the first index of array
	
	jal sort_function	#recursion call with index - 1
	
	#get ra and deallocate
	lw $ra, 0($sp) 
	addiu $sp, $sp, 4
	
	jr $ra
	
	first:
	sw $a0, 4($a1)		#stores num in first index of array
	jr $ra
	
	
	
	
	
	
