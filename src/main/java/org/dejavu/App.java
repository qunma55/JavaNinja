package org.dejavu;

import org.dejavu.validate.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       System.out.println(Validate.notNull(null, "The object must not be null"));
    }
}
