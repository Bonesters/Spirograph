CCFLAGS = javac

SourceFiles = ./src/spirograph/Main.java ./src/spirograph/Circle.java

LinkerFlags = -d ./bin

Debug: compile

compile:
			$(CCFLAGS) $(Executable) $(SourceFiles) $(LinkerFlags)
