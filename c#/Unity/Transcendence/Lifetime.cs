using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Lifetime : MonoBehaviour {
    public int lifetime;
    public int decayInterval;
	// Update is called once per frame
	void FixedUpdate () {
        lifetime--;
        if(lifetime%decayInterval == 0)
        {
            GetComponent<Light>().intensity = Mathf.Max(GetComponent<Light>().intensity - 1, 0);
        }
        
        if(lifetime < 1)
        {
            Destroy(gameObject);
        }
	}
}
