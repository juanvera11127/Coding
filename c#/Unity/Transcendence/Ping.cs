using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ping : MonoBehaviour {
    public GameObject pinger;
	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
    public void activate()
    {
        Instantiate(pinger, transform).transform.localPosition = new Vector3(0, 0, 0);
    }
}
