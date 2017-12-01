//
//  main.m
//  startoc
//
//  Created by zsn on 2017/7/29.
//  Copyright (c) 2017 apkfuns. All rights reserved.
//  http://www.cnblogs.com/jianglan/p/4122037.html
//

#import <Foundation/Foundation.h>
#import "Fraction.h"
#import "Calculator.h"
#import "ExtendDemo.h"
#import "Reatangle.h"


BOOL isSame(int num1, int num2) {
    if (num1 == num2) {
        return YES;
    }
    return NO;
}

NSString *boolString(BOOL yesNO) {
    if (!yesNO) {
        return (@"NO");
    } else {
        return (@"YES");
    }
}

int main(int argc, const char *argv[]) {
    @autoreleasepool {
        // insert code here...
        int sum = 25 + 10;
        NSLog(@"Hello, World! \n Hello Object-C, sum=%i", sum);
    }
//    NSLog(@"Number same-> %@", boolString(isSame(10, 10)));

//    [Fraction main];
//    [Calculator main];
//    [Calculator main2];
    [ClassB main];
//    [Square main];

    return 0;

}
