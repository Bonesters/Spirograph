CCFLAGS = javac

Executable = S

SourceFiles = spirograph.java 

LinkerFlags =

all:compile

Debug: ccflags +=
Debug: compile

compile:
			$(CCFLAGS) $(Executable) $(SourceFiles) $(LinkerFlags)
