package org.springboot.sdk.jpa.model.dialect;

public class FastCustomSQLDialect extends org.hibernate.dialect.MySQL57InnoDBDialect{
	
	 public FastCustomSQLDialect() {

		 super();
		 this.registerFunction("bitand", new BitAndFunction());
		 this.registerFunction("bitor", new BitOrFunction());
	}

}
