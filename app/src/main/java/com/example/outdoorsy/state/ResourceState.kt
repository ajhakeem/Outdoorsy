package com.example.outdoorsy.state

sealed class ResourceState {
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
    object LOADING : ResourceState()
}