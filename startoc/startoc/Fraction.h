//
// Created by zsn on 2017/8/24.
// Copyright (c) 2017 apkfuns. All rights reserved.
//

#import <Foundation/Foundation.h>

// 接口定义
@interface Fraction : NSObject

// 实例方法
- (void)print;

- (void)setNum:(int)n;

- (void)setDenominator:(int)d;

// 类方法，相当于Java静态方法
+ (void)getClass;

- (double)returnTypeAndMultiArgs:(int)x second:(double)y;

+ (void) main;
@end

// 实现类
@implementation Fraction {
    int num;
    int denominator;
}
- (void)print {
    NSLog(@"%i/%i", num, denominator);
}

- (void)setNum:(int)n {
    num = n;
}

- (void)setDenominator:(int)d {
    denominator = d;
}

+ (void)getClass {
    NSLog(@"print class Method");
}

- (double)returnTypeAndMultiArgs:(int)x second:(double)y {
    return y + x;
}

+ (void)main {
    [Fraction getClass];
//    Fraction *fraction1 = [[Fraction alloc] init];
    Fraction *fraction1 = [Fraction new];
//    fraction1 = [Fraction alloc];
//    fraction1 = [fraction1 init];
//    fraction1 = [[Fraction alloc] init];
    [fraction1 setNum:1];
    [fraction1 setDenominator:10];
    NSLog(@"double value = %lf", [fraction1 returnTypeAndMultiArgs:1 second:1.5]);
    NSLog(@"value is:");
    [fraction1 print];
}


@end
