//
// Created by zsn on 2017/8/25.
// Copyright (c) 2017 apkfuns. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Calculator : NSObject

// 赋值
- (void)setAccumulator:(double)d;

- (double)getAccumulator;

- (void)clear;

// 四则运算
- (double)add:(double)add;

- (double)subtract:(double)subtract;

- (double)multiply:(double)multiply;

- (double)divide:(double)divide;

// main方法
+ (void)main;

+ (void)main2;

@end

@implementation Calculator {
    double accumulator;
}
- (void)setAccumulator:(double)d {
    accumulator = d;
}

- (double)getAccumulator {
    return accumulator;
}

- (void)clear {
    accumulator = 0;
}

- (double)add:(double)add {
    return accumulator += add;
}

- (double)subtract:(double)subtract {
    return accumulator -= subtract;
}

- (double)multiply:(double)multiply {
    return accumulator *= multiply;
}

- (double)divide:(double)divide {
    return accumulator /= divide;
}

+ (void)main {
    Calculator *calculator = [Calculator new];
    [calculator setAccumulator:10.5];
    NSLog(@"value = %f", calculator.getAccumulator);
    NSLog(@"+%f=%f", 0.5, [calculator add:0.5]);
    NSLog(@"-%f=%f", 2.1, [calculator subtract:2.1]);
    NSLog(@"*%f=%f", 2.0, [calculator multiply:2.0]);
    NSLog(@"/%f=%f", 1.5, [calculator divide:1.5]);
}

+ (void)main2 {
    double value1, value2;
    char operator;
    NSLog(@"Type in your Expression");
    scanf("%lf %c %lf", &value1, &operator, &value2);
    Calculator *calculator = [Calculator new];
    [calculator setAccumulator:value1];
    switch (operator) {
        case '+':
            [calculator add:value2];
            break;
        case '-':
            [calculator subtract:value2];
            break;
        case '*':
        case 'x':
            [calculator multiply:value2];
            break;
        case '/':
            [calculator divide:value2];
            break;
        default:
            NSLog(@"Unknown operator");
    }
    NSLog(@"%1f %c %1f = %1f", value1, operator, value2, calculator.getAccumulator);
}


@end
