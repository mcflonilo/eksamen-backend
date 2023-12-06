# eksamen-backend
What I didn’t manage to get working was adding a machine with multiple subAssemblies using a post request

for some reason I get this error when reading the Json sent to my controller. It is some error with @RequestBody that I can’t figure out.

JSON parse error: Cannot deserialize value of type `com.example.eksamen_backend.model.Sub_assembly` from Object value (token `JsonToken.START_OBJECT`)]

To me it looks like the Json parser is expecting Subassembly to be a single object, but I cannot find the reason why. 

To fix the issue I have tried creating the customer object myself by parsing the request body using object mapper, but I get the same error.

I tried changing the relationship between machine and subassembly from a manytomany to a onetomany but I got the same error.

I tried playing around with the configuration of object mapper. But I couldn’t figure out what to tweak.

I tried changing the name of Subassembly to not have uppercase letters, and to not use underscores.

I am guessing that I have something wrong somewhere in my models but after scouring stack overflow for answers and staring at the for hours I give up.

The only workaround I found was changing the Json to not send subassemblies as an array but as a single object instead. This is kind of lame since I can’t have more than one Subassembly on a machine when posting. But adding additional ones after works fine.

Incredibly frustrating as everything else works perfectly fine when being sent as arrays.
