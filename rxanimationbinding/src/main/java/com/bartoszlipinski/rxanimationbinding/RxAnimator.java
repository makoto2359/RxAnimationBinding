/**
 * Copyright 2016 Bartosz Lipinski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bartoszlipinski.rxanimationbinding;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import rx.Observable;

import static com.bartoszlipinski.rxanimationbinding.internal.Preconditions.checkNotNull;

public class RxAnimator {

    @CheckResult
    @NonNull
    public static Observable<Animator> starts(Animator animator) {
        checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorListenerOnSubscribe(animator, AnimationEvent.START));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> ends(Animator animator) {
        checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorListenerOnSubscribe(animator, AnimationEvent.END));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> cancels(Animator animator) {
        checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorListenerOnSubscribe(animator, AnimationEvent.CANCEL));
    }

    @CheckResult
    @NonNull
    public static Observable<Animator> repeats(Animator animator) {
        checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorListenerOnSubscribe(animator, AnimationEvent.REPEAT));
    }

    @CheckResult
    @NonNull
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static Observable<Animator> pauses(Animator animator) {
        checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorPauseListenerOnSubscribe(animator, AnimationEvent.PAUSE));
    }

    @CheckResult
    @NonNull
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static Observable<Animator> resumes(Animator animator) {
        checkNotNull(animator, "animator == null");
        return Observable.create(new AnimatorPauseListenerOnSubscribe(animator, AnimationEvent.RESUME));
    }

    RxAnimator() {
        throw new AssertionError("No instances.");
    }
}
