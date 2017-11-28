//
// Created by zsn on 2017/8/26.
// Copyright (c) 2017 apkfuns. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface ClassA : NSObject {
    int x;
}
- (void)initValue:(int)d;
@end

@implementation ClassA
- (void)initValue:(int)d {
    x = d;
}
@end

@interface ClassB : ClassA
- (void)printValue;

+ (void)main;
@end

@implementation ClassB
// 类似构造函数
- (instancetype)init:(int)x {
    self = [super init];
    if (self) {
        [self initValue:x];
    }
    return self;
}


- (void)printValue {
    NSLog(@"Value = %i", x);
}

- (void)initValue:(int)d {
    x = d + 100;
}


+ (void)main {
//    ClassB *classB = [ClassB new];
//    [classB initValue:-12];

    ClassB *classB = [[ClassB alloc]init:10];
    [classB printValue];
}

@end