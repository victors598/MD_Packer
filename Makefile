all:
	javac runner.java Item.java Packer.java Reader.java Sorter.java

run:
	java runner

clean:
	rm *.class