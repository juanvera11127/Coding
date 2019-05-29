# Who:  Juan Vera
# What: encryption.asm
# Why:  A program to test IO programming,
#	file reading/writing, and using
#	buffers and strings for input/output.
# When: Created 4/25/19, Due 5/5/19
# How:  No registers except argument registers
#	used in main.
#	Encrypt uses $t0 to store input file code
#	$t1 holds output file code
#	$t2 holds the passphrase buffer
#	getPassphrase uses $t0 to store kernel address
#	$t1 holds the bytes being compared 

.data
.eqv		STRING_BUFFER_SZ		256
.eqv		PASSPHRASE_BUFFER_SZ		257


STRING_BUFFER:		.space			STRING_BUFFER_SZ
STRING_2_BUFFER:	.space			STRING_BUFFER_SZ
PASSPHRASE_BUFFER:	.space			PASSPHRASE_BUFFER_SZ

STRING_IN_PROMPT:	.asciiz			"Enter source file: "
STRING_OUT_PROMPT:	.asciiz			"Enter destination file: "
PASSPHRASE_PROMPT:	.asciiz			"Enter passphrase: "


.text
.globl main


main:    # program entry
	#prompt string
	la $a0, STRING_IN_PROMPT
	li $v0, 4
	syscall
	
	#get source file name
	la $a0, STRING_BUFFER		#buffer address arg
	la $a1, STRING_BUFFER_SZ	#buffer size arg
	jal getString			#call method
	
	#prompt destination file string
	la $a0, STRING_OUT_PROMPT
	li $v0, 4
	syscall
	
	#get destination file string
	la $a0, STRING_2_BUFFER
	la $a1, STRING_BUFFER_SZ
	jal getString
	
	#prompt passphrase
	li $v0, 4
	la $a0, PASSPHRASE_PROMPT
	syscall
	
	#get passphrase
	la $a0, PASSPHRASE_BUFFER
	li $a1, PASSPHRASE_BUFFER_SZ
	jal getPassphrase
	
	#encrypt file
	la $a0, STRING_BUFFER
	la $a1, STRING_2_BUFFER
	la $a2, PASSPHRASE_BUFFER
	jal encrypt


EXIT:
li $v0, 10        # terminate the program
syscall

#encrypt
#args:
	# $a0 = STRING_BUFFER
	# $a1 = STRING_2_BUFFER
	# $a2 = PASSPHRASE_BUFFER
#returns:
	#none - encrypts file

encrypt:

.data

.eqv    	FILE_BUFFER_SZ	         	1024
.eqv    	FILE_OPEN_CODE                	13
.eqv    	FILE_CLOSE_CODE               	16
.eqv   	 	FILE_READ_CODE                	14
.eqv     	FILE_WRITE_CODE      		15

FILE_BUFFER:       	.space         		FILE_BUFFER_SZ


.text
	#save buffers that will be overwritten
	move $t1, $a1
	move $t2, $a2

	# open source file
    	li $a1, 0			#read only
    	li $a2, 0
    	li $v0, FILE_OPEN_CODE
    	syscall

    	# test the descriptor for fault
    	move $t0, $v0			#store file descriptor
    	slt $a0, $t0, $0
    	bne $a0, $0, EXIT

    	# open destination file
    	move $a0, $t1			#string 2 buffer
    	li $a1, 1			#read/write
    	li $a2, 0
    	li $v0, FILE_OPEN_CODE
    	syscall

    	# test the descriptor for fault
    	move $t1, $v0			#store file descriptor
    	slt $a0, $t1, $0
    	bne $a0, $0, EXIT
    	
    	READ:
	# read buffer load of chars from source
    	li $v0, FILE_READ_CODE
    	move $a0, $t0
    	la $a1, FILE_BUFFER
    	li $a2, FILE_BUFFER_SZ		#num chars to read
    	syscall
    	
    	beq $0, $v0, CLOSE_RESOURCES	#exit if file read is empty
	
	XOR_START:
	move $a3, $t2			#reset pw buffer
	
	XOR_LOOP:
	lb $a2, 0($a3)			#get pw char
	lb $a0, 0($a1)			#get file char
	
	beqz $a2, XOR_START		#check if end of passphrase
	beqz $a0, EXIT_XOR		#check if end of file buffer
	
	beq $a2, $a0, SKIP		#check if chars are the same
					#because xoring will return null
	xor $a2, $a2, $a0		#xor both chars
	
	sb $a2, 0($a1)			#store original character to get original
					#back when decrypting
	SKIP:
	#increment buffers
	addiu $a3, $a3, 1
	addiu $a1, $a1, 1
	
	j XOR_LOOP
	
	EXIT_XOR:	
	#write into destination
    	move $a0, $t1
    	la $a1, FILE_BUFFER
    	move $a2, $v0		#num chars to write
    	li $v0, FILE_WRITE_CODE
    	syscall
    		
	j READ


CLOSE_RESOURCES:
    	li $v0, FILE_CLOSE_CODE
    	move $a0, $t0			#close source file
    	syscall

    	li $v0, FILE_CLOSE_CODE
    	move $a0, $t1			#close dest file
    	syscall

	jr $ra

#getString: 
#args:	
	#a0 has buffer address
	#a1 has buffer size
#returns:
	#nothing - buffer address contains string
getString:

.data
.eqv		ENTER_KEY			10

.text	
	#read string
	li $v0, 8
	syscall
	
	#look for enter key
	getString_loop:
	
	lb $a2, 0($a0)			#get char
	beqz $a2, exit_getString	#check if end of string
	li $a3, ENTER_KEY		
	beq $a2, $a3, enter_switch	#check if char is enter key
	
	addiu $a0, $a0, 1		#increment
	
	j getString_loop
	
	enter_switch:
	sb $0, 0($a0)			#store null into enter key spot
					#in order to access file correctly
	
	exit_getString:
	jr $ra				#return
	
#getPassphrase
#args:	
	#a0 has pass buffer
	#a1 has buffer size
#returns:
	#none - passphrase string is in buffer
getPassphrase:

.data
.eqv		KERNEL				0xffff0000
.eqv		ASTERISK			42
.eqv		ENTER_KEY			10	

.text
	pass_loop:
    	addiu $a1, $a1, -1		#increment before branch to fix offset
	beqz $a1, exit_passphrase	#exit if reached size limit
	
	li $t0, KERNEL			#load kernal address base
	
	#poll loop
	CkReady:
	lbu $t1, 0($t0)			#load input byte
	andi $t1, $t1, 1		#extract ready bit
	beqz $t1, CkReady		#loop if not ready
	
	lb $a2, 4($t0)			#get input value 
	beq $a2, ENTER_KEY, exit_passphrase		#check if value is enter key
	sb $a2, 0($a0)			#else store byte into buffer
	addiu $a0, $a0, 1		#increment

	#poll loop
	XReady:
	lbu $t1, 8($t0)			#loud output byte
	andi $t1, $t1, 1		#extract ready bit
	beqz $t1, XReady		#loop if not ready
	
	#load asterisk into display
	li $t1, ASTERISK
	sb $t1, 12($t0)					
	j pass_loop			#loop for next char input
	
	exit_passphrase:
	jr $ra				#return
