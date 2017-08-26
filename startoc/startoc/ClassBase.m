//
// Created by zsn on 2017/8/26.
// Copyright (c) 2017 apkfuns. All rights reserved.
//

#import "ClassBase.h"


@implementation ClassBase {

}

@synthesize goodTime;

- (void)print {
    goodTime = 1;
}


- (void)multiArgs:(int)d :(double)x {
    NSLog(@"%i,%f", d, x);
}

- (void)multiArgs2:(int)d key:(double)x {
    NSLog(@"%i,%f", d, x);
    [self multiArgs:d :x];
}

- (void)objectArgs:(ClassBase *)arg {
    [arg print];
}


+ (void)main {
    ClassBase *base = [ClassBase new];
    base.goodTime = 1;
    [base print];
    [base multiArgs:10 :10.2];
    [base multiArgs2:10 key:13.1];
}

@end