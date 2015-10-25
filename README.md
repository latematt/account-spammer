# AccountSpammer

A program written in Java that sends POST requests to any PHP script.
# Usage
Make a new instance of AccountSpammer as such:

    AccountSpammer accountSpammer = new AccountSpammer("http://website.com/script.php", "argument1=test", "argument2=lol");

Then call the method AccountSpammer#postData after this instance has been called:

    accountSpammer.postData();

Finally, get the reply from the POST request:

    String[] reply = accountSpammer.getReply();

If you'd like an example on how to spam a register page using this library, then check out SpamExample#main. But, if you'd like an example on how to get the reply from the POST data, then check out ReplyExample#main

# Installation

First, add the library to your project somehow. For maven users, copy this into your pom:

    <repositories>
        <repository>
            <releases>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
				<checksumPolicy>fail</checksumPolicy>
            </releases>
            <id>latematt-pw-repository</id>
            <name>latematt.pw repository</name>
            <url>http://latematt.pw/maven</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>pw.latematt</groupId>
            <artifactId>account-spammer</artifactId>
            <version>1.2.0</version>
        </dependency>
    </dependencies>

Otherwise, include the library provided [here](http://latematt.pw/maven/pw/latematt/account-spammer/1.2.0)