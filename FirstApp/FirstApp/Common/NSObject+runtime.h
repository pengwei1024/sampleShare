//
// Created by pengwei on 2017/9/2.
// Copyright (c) 2017 hiandroid. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSObject (runtime)

+ (NSArray *)getAllProperties;

+ (NSArray *)getAllMethods;

+ (NSDictionary *)getAllPropertiesAndVaules:(NSObject *)obj;
@end