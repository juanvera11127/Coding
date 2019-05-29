.data

MyData:		.space		200
MyDataSz:	.word		50

la $t0, MyData		#load array
addu $t1, $t0, $t1	#set sum to 0
addu $t2, $t0, $t2	#set count to 0

loop:

bge $t2, 100, exit_loop	#loop from 0-99

lw $t3, 0($t0)
addu $t1, $t1, $t3	#add current index to sum

addu $t0, $t0, 4	#increment
addu $t2, $t2, 1	#increment

j loop

exit_loop:

la $t0, MyDataSz	#t0 = size address
lw $t0, 0($t0)		#t0 = size value
sw $t1, MyData($t0)	#store counter in array + size address  