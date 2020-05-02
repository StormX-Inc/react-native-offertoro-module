# React Native module for OfferToro

This module contains the native Android implementation. The iOS one is under the React Native app (`ios/OfferToroModule.m`)

OfferToroModule.h:
```
#ifndef OfferToroModule_h
#define OfferToroModule_h

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import <OffertoroSDK/OfferToroSDK.h>

@interface OfferToroModule : RCTEventEmitter <RCTBridgeModule>

@end

#endif /* OfferToroModule_h */
```

OfferToroModule.m:
```
#import <React/RCTUtils.h>
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "OfferToroModule.h"
#import <OffertoroSDK/OfferToroSDK.h>

@import AdSupport;

@implementation OfferToroModule

RCT_EXPORT_MODULE()

- (NSArray<NSString *> *)supportedEvents
{
  return @[@""];
}

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}

RCT_EXPORT_METHOD(showOfferwall:(NSString *)appId secretKey:(NSString *)secretKey userId:(NSString *)userId)
{
  [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(offertoroOWInitSuccess) name:@"offertoroOWInitSuccess" object:nil];
  [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(offertoroOWInitFail:) name:@"offertoroOWInitFail" object:nil];
  [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(offertoroOWOpened) name:@"offertoroOWOpened" object:nil];
  [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(offertoroOWClosed) name:@"offertoroOWClosed" object:nil];
  
  [[OfferToro sharedInstance] initOWWithAppId:appId userId: userId secretKey:secretKey];
  
  UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
  [[OfferToro sharedInstance] presentOWInViewController:rootViewController animation:false];
}

-(void)offertoroOWInitSuccess{
  NSLog(@"offertoroOWInitSuccess");
}

- (void)offertoroOWInitFail:(NSError *)error{
  NSLog(@"offertoroOWInitFail %@", error);
}

- (void)offertoroOWOpened{
  NSLog(@"offertoroOWOpened");
}

- (void)offertoroOWClosed{
  NSLog(@"offertoroOWClosed");
}

@end
```

React Native:
```
const { OfferToroModule } = NativeModules;

OfferToroModule.showOfferwall(offerToroAppKey, offerToroSecretKey, userId);
```

