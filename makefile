CCFLAGS = javac

SourceFiles = ./src/spirograph/Main.java 

LinkerFlags = -d ./bin

Debug: compile

compile:
			$(CCFLAGS) $(Executable) $(SourceFiles) $(LinkerFlags)
